# Ejercicio entrevista: Cupón

Mercado Libre está implementando un nuevo beneficio para los usuarios que más usan la
plataforma con un cupón de cierto monto gratis que les permitirá comprar tantos items
marcados como favoritos que no excedan el monto total. Para esto se está analizando
construir una API que dado una lista de item_id y el monto total pueda darle la lista de items
que maximice el total gastado sin excederlo

# Tecnologías usadas: Reto Cupón
	Java 1.8
	Gradle
	Spring Boot 2.5.2
	Junit 5
	Heroku
	
# API

	https://whispering-forest-20515.herokuapp.com/coupon
		
		Tipo: POST
		
		- Respuesta para items gasto maximo: 200 (OK)
		
		- Respuesta al no encontrar distribución de gasto máximo: 404 (NOT_FOUND)
		
		- Respuesta al tener otro tipo de errores: 400 (BAD_REQUEST)
		
		Body ingreso:
			
			{
				"item_ids": ["MLA803174898", "MLA803174894", "MLA803174788", "MLA803086664", "MLA810645375", "MLA844702264"],
				"amount": 1500
			}
		
	https://whispering-forest-20515.herokuapp.com/
	
		Tipo: GET
		
		- Endpoint de prueba para deploy: 200 (OK)
		
	https://whispering-forest-20515.herokuapp.com/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/coupon-controller/evaluateCoupon
		
		Documentación con Swagger.
