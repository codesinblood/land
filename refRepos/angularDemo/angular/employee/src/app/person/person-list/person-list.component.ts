import { Component, OnInit, OnDestroy } from '@angular/core';
import {PersonService} from '../person-service';
import {Person} from '../person.model';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit, OnDestroy {

  employee: Person[];
  submittedPerson: Person;
  url: string;

  constructor(private service: PersonService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.getPersons();
    this.service.submittedPerson.subscribe(
      (person: Person) => {
        this.submittedPerson = person;
        this.getPersons();
      }
    );
  }

  getPersons() {
    this.url = this.router.url;
    const urlList = this.url.split('/');
    this.service.getPersons()
        .subscribe(data => {
          this.employee = <Person[]>data;
          if (urlList[urlList.length - 1] === 'new' && this.submittedPerson === undefined) {
            this.router.navigate(['new'], {relativeTo: this.route});
          } else if (this.submittedPerson !== undefined) {
            this.router.navigate([ this.submittedPerson.id], {relativeTo: this.route});
          } else {
            this.router.navigate([this.employee[0].id], {relativeTo: this.route});
            // this.router.navigate([urlList[urlList.length - 1]], {relativeTo: this.route});
          }
      });
  }

  ngOnDestroy() {
    this.service.submittedPerson.unsubscribe();
  }
}
