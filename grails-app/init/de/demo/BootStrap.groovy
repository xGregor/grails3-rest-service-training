package de.demo

class BootStrap {

    def init = { servletContext ->
        new Car(make: "BMW", model: "318i").save()
    }
    def destroy = {
    }
}
