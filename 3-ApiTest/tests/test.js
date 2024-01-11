const chakram = require('chakram'), expect = chakram.expect;

describe("User Module", function() {
    it("Get User Login", function () {
        const response = chakram.get("https://petstore.swagger.io/v2/user/login?username=Buse&password=123456");
        expect(response).to.have.status(200);
        return chakram.wait();

     });

     it("New User Creation", function () {
        const body= [
            {
              "id": 1646861,
              "username": "Busee12",
              "firstName": "Buse",
              "lastName": "Sayin",
              "email": "busesayin@test.com",
              "password": "123456",
              "phone": "05489568712",
              "userStatus": 0
            }
          ]

        const response = chakram.post("https://petstore.swagger.io/v2/user/createWithArray", body);
        expect(response).to.have.status(200);
        return chakram.wait();

    
     });

     it("Updating Username", function () {

        const body = {
            "id": 1646861,
            "username": "Buseee1212",
            "firstName": "Buse",
            "lastName": "Sayin",
            "email": "busesayin@test.com",
            "password": "123456",
            "phone": "05489568712",
            "userStatus": 0
          }

        const response = chakram.put("https://petstore.swagger.io/v2/user/Busee12", body);
             expect(response).to.have.status(200);
            return chakram.wait();

    });

    it("New User List Creation", function () {
        const body=[
            {
              "id": 3,
              "username": "buse123",
              "firstName": "bus",
              "lastName": "say",
              "email": "busesayin9935@gmail.com",
              "phone": "05368574561",
              "userStatus": 1
            }
          ]
    const response = chakram.post("https://petstore.swagger.io/v2/user/createWithList", body);
         expect(response).to.have.status(200);
        return chakram.wait();

    });     

});

describe("Pet Module", function() {
    it("Pet Creation", function () {
        const body = {
            "id": 2,
            "category": {
              "id": 2,
              "name": "köpiş"
            },
            "name": "doggie",
            "photoUrls": [
              "wqklnglqknglqwknwgq"
            ],
            "tags": [
              {
                "id": 2,
                "name": "köpiş"
              }
            ],
            "status": "available"
          }
    const response = chakram.post("https://petstore.swagger.io/v2/pet", body);
         expect(response).to.have.status(200);
        return chakram.wait();

    });

    it("Pet Information", function () {
        const response = chakram.get("https://petstore.swagger.io/v2/pet/2");
             expect(response).to.have.status(200);
            return chakram.wait();

     });

     it("Delete Pet", function () {
        const response = chakram.delete("https://petstore.swagger.io/v2/pet/2");
             expect(response).to.have.status(200);
            return chakram.wait();

        });
    });
          