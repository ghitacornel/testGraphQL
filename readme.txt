--POST
http://localhost:8080/graphql

--QUERY

query ExampleQuery {
  vehicles {
    id
    brandName
    modelCode
    type
  }
}

--MUTATION
mutation CreateVehicle($modelCode: String!, $type: String!, $brandName: String) {
  createVehicle(modelCode: $modelCode, type: $type, brandName: $brandName, ) {
    modelCode
    type
    brandName
  }
}

--MUTATION variables
{
  "modelCode": "1310",
  "type": "A",
  "brandName": "dacia"
}

--SCHEMA
http://localhost:8080/graphql/schema.json