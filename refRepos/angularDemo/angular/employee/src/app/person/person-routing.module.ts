import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PersonListComponent } from './person-list/person-list.component';
import { PersonInfoComponent } from './person-info/person-info.component';

const personRoutes: Routes = [
  { path: '', component: PersonListComponent, children: [
    { path: 'new', component: PersonInfoComponent },
    { path: ':id', component: PersonInfoComponent }
  ] }
];

@NgModule({
  imports: [
    RouterModule.forChild(personRoutes)
  ],
  exports: [RouterModule]
})
export class PersonRoutingModule {

}
