import internal.GlobalVariable

String status = "pending"

String id = CustomKeywords.'com.patron.AddAndCheckNewPet.addNewDog'()
CustomKeywords.'com.patron.AddAndCheckNewPet.checkDogByID'(id)
CustomKeywords.'com.patron.AddAndCheckNewPet.updateDogByID'(id, status)
CustomKeywords.'com.patron.AddAndCheckNewPet.deleteDogByID'(id)
