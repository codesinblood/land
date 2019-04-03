import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-address-entity',
  templateUrl: './address-entity.component.html',
  styleUrls: ['./address-entity.component.css']
})
export class AddressEntityComponent implements OnInit {

  constructor() { }
  selectedItem: any;
  isAddSelect;

  ngOnInit() {
  }
  onItemSelect(event: any) {
    this.selectedItem = event;
  }
  onAddSelect(event: any) {
    this.isAddSelect = event;
  }
}
