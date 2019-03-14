# cm-acme-backend

REST API:

POST /rest/app/signup
{"name":"Mila","fiscalNumber":"100000004","password":"Abc-123"}

POST /rest/app/login
{"name":"Mila","password":"Abc-123"}

GET /rest/app/cart?id=6

GET /rest/app/product?barcode=1

POST /rest/app/cart/item/add
{"productId":1,"number":2,"userId":6}

DELETE /rest/app/cart/item/delete?id=502

DELETE /rest/app/cart/clean?id=6

GET /rest/app/payment/do?id=6

GET /rest/printer/receipt?token=9C7FE7B3-F007-44AB-868D-D373075FD7C0_PM_1552597941104
