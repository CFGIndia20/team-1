from flask import Flask, request
from twilio.twiml.messaging_response import MessagingResponse
import pyrebase
from fpdf import FPDF
from config import keys

app = Flask(__name__)


firebaseConfig = keys
firebase = pyrebase.initialize_app(firebaseConfig)
db = firebase.database()
storage = firebase.storage()

'''
create a report in realtime based on the unit which the doner is associated with it
'''


def create_report(unit_no):
    pdf = FPDF()
    pdf.add_page()
    pdf.set_font("Arial", size=15)
    pdf.cell(200, 10, txt="The St. Jude India Childcare Centre", ln=1, align='C')
    pdf.cell(200, 10, txt="2020 Report of Unit: " + str(unit_no), ln=2, align='C')
    u = db.child("units").get().each()[unit_no]

    pdf.cell(200, 10, txt=f"", ln=1, align='C')
    pdf.cell(200, 10, txt=f"", ln=1, align='C')
    pdf.cell(200, 10, txt=f"Location: {u.val()['location']}", ln=1, align='C')
    pdf.cell(200, 10, txt=f"Patients treated till now {u.val()['prev_patient']}", ln=1, align='C')
    pdf.cell(200, 10, txt=f"Patients currently admitted {u.val()['curr_patient']}", ln=1, align='C')
    r_name = "report.pdf"
    pdf.output(r_name)
    storage.child(r_name).put(r_name)
    return storage.child(r_name).get_url(None)


'''
whatsapp endpoint for doner
- registerd user can get the pdf report link
'''


@app.route("/sms", methods=['POST'])
def sms_reply():
    resp = MessagingResponse()
    found = False
    user = db.child("doner").get().each()
    for u in user:
        if u.val()['phone'] in request.form.get('From'):
            user_data = u.val()
            found = True
            break

    else:
        w_resp = 'You are currently not registered as a doner with our organization, feel free to donate and addplease visit \n https://www.stjudechild.org/donate.aspx '

    if found:
        if 'report' in request.form.get('Body') or 'Report' in request.form.get('Body'):
            w_resp = 'link:' + create_report(int(user_data['unit']))
        else:
            w_resp = f"Hello {user_data['name']}\nEmail: {user_data['email']}\nUnit: {user_data['unit']}\nIf you want to see the report of your unit, please reply somthing like:\n'I want the report' or 'send me the report'\nThank you for your contibution :)"
    resp.message(w_resp)
    return str(resp)


if __name__ == "__main__":
    app.run(debug=True)
