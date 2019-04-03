import { Component, OnInit, Output, EventEmitter, Input, AfterContentInit, AfterViewInit, DoCheck } from '@angular/core';
import { Person } from '../person.model';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { PersonService } from '../person-service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-person-info',
  templateUrl: './person-info.component.html',
  styleUrls: ['./person-info.component.css']
})
export class PersonInfoComponent implements OnInit {

  person: Person = new Person();
  id: number;
  constructor( private route: ActivatedRoute, private service: PersonService, private router: Router) {
  }
  ngOnInit() {
    this.route.params
      .subscribe(
        (params: Params) => {
          if (params['id']) {
          this.id = +params['id'];
          this.service.getPerson(this.id).subscribe(
            (res: Person) => { this.person = res; }
          );
        } else {
          this.person = new Person();
        }
      }
      );
  }
  onSubmitSelect(submit: NgForm) {
    this.service.createPerson(this.person).subscribe(
      (res: Person) => {
        this.service.submittedPerson.next(res);
      }
    );
  }
}
