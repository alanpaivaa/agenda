package br.com.ajeferson.client

import br.com.ajeferson.corba.AgendaPOA
import br.com.ajeferson.entity.Contact
import io.reactivex.subjects.PublishSubject

class AgendaImpl(val id: String): AgendaPOA() {

    val insertStream: PublishSubject<Contact> = PublishSubject.create()
    val removeStream: PublishSubject<String> = PublishSubject.create()

    override fun isAlive(): Boolean {
        return true
    }

    override fun insert(name: String?, phoneNumber: String?): Boolean {

        if(name == null || phoneNumber == null) {
            return false
        }

        insertStream.onNext(Contact(name, phoneNumber))

        return true

    }

    override fun remove(name: String?): Boolean {

        if(name == null) {
            return false
        }

        removeStream.onNext(name)

        return true

    }

}