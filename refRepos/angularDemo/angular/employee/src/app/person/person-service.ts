import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Person } from './person.model';
import { Subject } from 'rxjs';

@Injectable()
export class PersonService {
  submittedPerson = new Subject();
  constructor(private http: HttpClient) { }

  getPersons() {
    return this.http.get('http://localhost:8080/ws/do/person');
  }

  getPerson(id: number) {
    return this.http.get('http://localhost:8080/ws/do/person?id=' + id);
  }

  createPerson(person: Person) {
    return this.http.put('http://localhost:8080/ws/do/person', person);
  }
}
