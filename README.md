# credibanco-back

# curls de peticiones
curl --location --request POST 'http://localhost:8080/card' \
--header 'Content-Type: application/json' \
--data-raw '{
    "numTarjeta":1234567892356465,
    "titular":"Daniel",
    "numIdentificacion":"5432198672",
    "numTelefono":5555555555,
    "tipoTarjeta":"credito"
}'

curl --location --request PUT 'http://localhost:8080/enrolar' \
--header 'Content-Type: application/json' \
--data-raw '{
    "numTarjeta":1234567892356465,
    "numeroValidacion":50
}'

curl --location --request GET 'http://localhost:8080/tarjeta?numTarjeta=1234567892356465'

curl --location --request DELETE 'http://localhost:8080/tarjeta' \
--header 'Content-Type: application/json' \
--data-raw '{
    "numTarjeta":1234567892356465,
    "numeroValidacion":79
}'

curl --location --request POST 'http://localhost:8080/transaccion' \
--header 'Content-Type: application/json' \
--data-raw '{
    "valorCompra":10000,
    "numTarjeta":1234567892356465,
    "numReferencia":123456,
    "direccionCompra":"cr 1 #2 -3"
}'

curl --location --request PUT 'http://localhost:8080/transaccion' \
--header 'Content-Type: application/json' \
--data-raw '{
    "numeroTarjeta":1234567892356465,
    "numReferencia":1234567,
    "totalCompra":10000
}'

curl --location --request GET 'http://localhost:8080/transacciones?numTarjeta=1234567892356465'

#url de la base de datos H2
http://localhost:8080/h2-console
usuario:sa
contraseña:password

#url de actuatuors
http://localhost:8080/actuators

#url de proyecto front
http://localhost:4200/