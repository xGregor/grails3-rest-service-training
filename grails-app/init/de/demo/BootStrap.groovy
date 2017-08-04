package de.demo

class BootStrap {

    def init = { servletContext ->
        new Car(make: "BMW", model: "318i").save()
        new Person(firstName: "Michael", lastName: "Mustermann").save()
        new Person(firstName: "Michaela", lastName: "Musterfrau").save()
    }
    def destroy = {
    }
}
