package de.demo

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class PersonController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond Person.list(params), model:[personCount: Person.count()]
         [persons: Person.list(params), personen: Person.list(params)]
    }

    def show(Person person) {
        respond person
    }

    @Transactional
    def save(Person person) {
        if (person == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (person.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond person.errors, view: 'create'
            return
        }

        person.save flush: true

        respond person, [status: CREATED, view: "show"]
    }

    @Transactional
    def update(Person person) {
        if (person == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (person.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond person.errors, view: 'edit'
            return
        }

        person.save flush: true

        respond person, [status: OK, view: "show"]
    }

    @Transactional
    def delete(Person person) {

        if (person == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        person.delete flush: true

        render status: NO_CONTENT
    }
}
