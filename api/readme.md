## There are two endpoints in the api ##
1. '/sms' --> whatsapp bot send all the requests to this endpoint
2. '/pdf_link/<int:unit_num>' --> for web/android to get the report based on the unit of the doner

                Hi/Hello
                    |
                    |
                    V
            If senders mobile
            number is registered _______________
                    |                           |
                    |                           |
                    V                           V
                Greetings               Encourage them to donate and
                    |                   direct them to st. jude home page
                    |
                    V
                    Doner ask for report
                    |
                    |
                    V
        provide the dynamically created
            report to the user
