package com.patron

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static org.assertj.core.api.Assertions.*
import static com.kms.katalon.core.util.KeywordUtil.logInfo

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import internal.GlobalVariable as GlobalVariable


public class AddAndCheckNewPet {


	ResponseObject response

	def addNewDog = {

		response = WS.sendRequest(findTestObject('POST New Pet'))
		WS.verifyResponseStatusCode(response, 200)
		WS.verifyElementPropertyValue(response, 'category.name', GlobalVariable.DogName)

		return (String) WS.getElementPropertyValue(response, 'id')
	}

	def checkDogByID = { String id ->
		RequestObject getRequest = findTestObject('GET Pet by ID')
		String getRequestUrl = getRequest.getRestUrl().replace('{id}', id)
		getRequest.setRestUrl(getRequestUrl)
		response = WS.sendRequest(getRequest)

		logInfo("Dog Name: " + (String) WS.getElementPropertyValue(response, 'category.name'))
		logInfo("Dog Status: " + (String) WS.getElementPropertyValue(response, 'status'))

		WS.verifyResponseStatusCode(response, 200)
		WS.verifyElementPropertyValue(response, 'category.name', GlobalVariable.DogName)
	}

	def updateDogByID = { String id, String status ->
		RequestObject putRequest = findTestObject('PUT Update Pet')
		String putRequestUrl = putRequest.getRestUrl().replace('{id}', id)
		String requestBody = """
			{
				"id": 0,
				"category": {
					"id": 0,
					"name": "${GlobalVariable.DogName}"
					},
				"name": "${GlobalVariable.DogName}",
				"photoUrls": [
					""
				],
				"tags": [
					{
						"id": 0,
						"name": "stafford"
					}
				],
				"status": "${status}"
			}
		"""
		putRequest.setHttpBody(requestBody)
		response = WS.sendRequest(putRequest)

		logInfo("Dog Name: " + (String) WS.getElementPropertyValue(response, 'category.name'))
		logInfo("Status: " + (String) WS.getElementPropertyValue(response, 'status'))

		WS.verifyResponseStatusCode(response, 200)
		WS.verifyElementPropertyValue(response, "status", status)
	}

	def deleteDogByID = { String id ->
		RequestObject deleteRequest = findTestObject('DELETE Pet by ID')
		String deleteRequestUrl = deleteRequest.getRestUrl().replace('{id}', id)
		deleteRequest.setRestUrl(deleteRequestUrl)
		response = WS.sendRequest(deleteRequest)

		WS.verifyResponseStatusCode(response, 200)
	}
}