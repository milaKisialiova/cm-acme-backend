### cm-acme-backend

REST API:

# POST /rest/app/signup
{"name":"Max","address":"Porto","fiscalNumber":"123456781","password":"Abc-123","card":{"type":"VISA","number":"1234123412341234","holderName":"Max Pupkin","validDate":"0222","validity":"123"},"publicRsaKey":"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCZ9GYfiHl/1W8mmhOaCAabdX7rw3Ae2YCgzmpMVOLZKxjd0w8YV3aL3kWtmfYs3stc/o8MVZ/LUakCMqr/zzfKYA2eVJKkY9LfQOcQwWrDFCj0JN79f1hs7C91IDkQLa6QuDXTtbzT87UQfWmBL2LUMELQ9XqurT8Wx/C0t+tL9QIDAQAB"}

response:
{"errorCode": null}

# POST /rest/app/login
{"name":"Mila","password":"Abc-123"}

response:
{"errorCode: null, "token":"eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJWYXNpbGl5MiIsImlhdCI6MTU1NjE4MzY3NiwiZXhwIjoxNTU2MTg0Mjc2fQ.Tuahhx1zADtlgmdd3yYOfufYstAJUkD3rQF275UsARPoQ44Z-0y6dZlIbTS_I4BVXCefk6zsVL7rWY1ejjR0Dxo12kObKgd3Ugwk-SgBq57KYqHekYHPMwRrOKvrvs2gSzNLXVoDQGvVdN4P0ELdjBZa-DVwVABfb2hv6-qKoA8"}


# GET /rest/app/cart
Header Authorization: "Bearer eyJhbGciOiJSU...-A"

response:
{"errorCode":null,"items":[{"items":[{"id":1807,"product":{"id":1,"name":"TV samsung","price":1200,"descrition":"Samsung-TV22-SW00K"},"number":2}],"id":1806,"totalPrice":7200,"cartStatus":"active"}]}

# GET /rest/app/product?barcode=1

response
{"errorCode":null,"id":1,"name":"TV samsung","price":1200,"descrition":"Samsung-TV22-SW00K"}

# POST /rest/app/cart/item/add
{"productId":1,"number":2}
Header Authorization: "Bearer eyJhbGciOiJSU...-A"

response:
{"errorCode": null}

# DELETE /rest/app/cart/item/delete?id=1817
Header Authorization: "Bearer eyJhbGciOiJSU...-A"

response:
{"errorCode": null}

# DELETE /rest/app/cart/clean
Header Authorization: "Bearer eyJhbGciOiJSU...-A"

response:
{"errorCode": null}


# GET /rest/app/payment/do?token=eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJQYXltZW50In0.OHJ13oCbDs2Vn99IKISI7lyAFyYi47KVa4bPDSIw0WjboUhCqCL6mFAPOJgmA31ONBNd4GuSvjhgDU5DokERh5OpRX-HFE3KE4PswdbVIQc5jFUpE8SkC8Us8aOIupyHHJQXc0tDUB-EI80p8v0HR9kSNCFjUcIdZ7ZJBVsSYsc
Header Authorization: "Bearer eyJhbGciOiJSU...-A"

response:
{"errorCode":null,"token":"18248D63-EDF2-4EF3-9F35-811EC6BBE6A7_PM_1556191220600","date":1556191220744,"totalAmount":0.0,"memo":"RECEIPT\nPayment status: OK\nCard: 4*5612\nItems: \n------------------\nTotal:   0\n"}

# GET /rest/printer/receipt?id=9C7FE7B3-F007-44AB-868D-D373075FD7C0_PM_1552597941104

response:
{"errorCode": null, "memo": "RECEIPT Payment status: OK Card: 4*5612 Items: Headphones * 1 420 TV samsung * 3 3600 TV samsung * 2 2400 ------------------ Total: 6420 "}
