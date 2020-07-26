from flask import Flask, request
from twilio.twiml.messaging_response import MessagingResponse
import pyrebase
from fpdf import FPDF

config = {
  "apiKey": "AIzaSyDZKPNKXkW_R8yUog4onZNm1VDVQuwTPcc",
  "authDomain": "aashray-3d07c.firebaseapp.com",
  "databaseURL": "https://aashray-3d07c.firebaseio.com",
  "projectId": "aashray-3d07c",
  "storageBucket": "aashray-3d07c.appspot.com",
  "messagingSenderId": "417279724498",
  "appId": "1:417279724498:web:66fc11b9ab02c5dbee560b",
  "measurementId": "G-WHC1XZ7MFQ"
}
app = Flask(__name__)


# firebaseConfig = keys
firebase = pyrebase.initialize_app(config)
db = firebase.database()
storage = firebase.storage()


# create a report in realtime based on the unit which the doner is associated with it


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
whatsapp bot endpoint for doner
- registerd user can get the pdf report link
- non members will get a link to to donate and become a memeber
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
        w_resp = 'You are currently not registered as a doner with our organization, feel free to donate and keep our cancer warrior safe..\nFor more information please visit:\nhttps://www.stjudechild.org\nTo donate please visit:\nhttps://www.stjudechild.org/donate.aspx '

    if found:
        if 'report' in request.form.get('Body') or 'Report' in request.form.get('Body'):
            w_resp = 'link:' + create_report(int(user_data['unit']))
        else:
            w_resp = f"Welcome {user_data['name']},\nEmail: {user_data['email']}\nUnit: {user_data['unit']}\nIf you want to see the report of your unit, please reply somthing like:\n'I want the report' or 'send me the report'\nThank you for your contibution :)"
    resp.message(w_resp)
    return str(resp)


# endpoint to get a custumized progress report based on the unit number provided
@app.route("/pdf_link/<int:unit_num>", methods=['POST'])
def get_pdf(unit_num):
    return create_report(unit_num)


if __name__ == "__main__":
    app.run(debug=True)
