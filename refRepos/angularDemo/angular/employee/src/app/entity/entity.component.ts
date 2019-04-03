import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-entity',
  templateUrl: './entity.component.html',
  styleUrls: ['./entity.component.css']
})
export class EntityComponent implements OnInit {

  // @Output() entityChange = new EventEmitter<string>();
  // selectedEntity: string;
  // constructor() { }

  ngOnInit() {
  }
  // onEntitySelect(entity: string) {
  //   this.selectedEntity = entity;
  //   this.entityChange.emit(this.selectedEntity);
  // }
}
