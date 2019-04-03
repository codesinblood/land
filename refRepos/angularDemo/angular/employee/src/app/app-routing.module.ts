import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EntityComponent } from './entity/entity.component';

const appRoutes: Routes = [
  { path: '', redirectTo: 'person', pathMatch: 'full'},
  { path: '', component: EntityComponent, children: [
    { path: 'person', loadChildren: './person/person.module#PersonModule'}
  ]},
  { path: '**', redirectTo: '/person', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})

export class AppRoutingModule {

}
