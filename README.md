### JSON Server is a Node Module that you can use to create demo rest json webservice in less than a minute. All you need is a JSON file for sample data.

- Install

        npm install -g json-server

    Verify

        cd C:\Users\student\AppData\Roaming\npm
        json-server -v

- Create file (db.json)

        {
        "employees": [
            {
            "id": 1,
            "name": "Pankaj",
            "salary": "10000"
            },
            {
            "id": 2,
            "name": "Lisa Tamaki",
            "salary": "20000"
            },
            {
            "id": 3,
            "name": "Andrej Skeledzija",
            "salary": "1234",
            "Children":[{"ChildName" : "Filip" },{"ChildName" : "Petra"}],
            "address": { 
                  "street": "Kulas Light",
                  "suite": "Apt. 556",
                  "city": "Gwenborough",
                  "zipcode": "92998-3874",
                  "geo": {
            "lat": "-37.3159",
            "lng": "81.1496"
                   }
            }
            }
                    ]
        }

- Create file (routes.json)

        {
        "/employees/list": "/employees",
        "/employees/get/:id": "/employees/:id",
        "/employees/create": "/employees",
        "/employees/update/:id": "/employees/:id",
        "/employees/delete/:id": "/employees/:id"
        }


- Run JSON server

        cd C:\Users\student\AppData\Roaming\npm
        json-server --port 7000 --routes routes.json --watch db.json

- Run Tests

        mvn test -P AllApiTests