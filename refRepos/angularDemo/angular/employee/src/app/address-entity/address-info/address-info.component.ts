import { Component, OnInit, Input, DoCheck, AfterContentInit } from '@angular/core';

@Component({
  selector: 'app-address-info',
  templateUrl: './address-info.component.html',
  styleUrls: ['./address-info.component.css']
})
export class AddressInfoComponent implements DoCheck {

  @Input() address;
  @Input() selectedItem: any;
  @Input() isAddSelect: any;
  constructor() { }

  ngDoCheck() {
    if (this.isAddSelect === true) {
      this.selectedItem = '';
      this.isAddSelect = false;
    }
  }

}
