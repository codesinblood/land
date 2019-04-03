import { NgModule } from '@angular/core';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonInfoComponent } from './person-info/person-info.component';
import { PersonRoutingModule } from './person-routing.module';
import { PersonService } from './person-service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@NgModule({
  declarations: [
    PersonListComponent,
    PersonInfoComponent
  ],
  imports: [
    PersonRoutingModule,
    HttpClientModule,
    FormsModule,
    CommonModule
  ],
  providers: [
    PersonService
  ]
})
export class PersonModule {

}
