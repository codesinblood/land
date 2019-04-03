import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {AddressService} from '../address-service';

@Component({
  selector: 'app-address-list',
  templateUrl: './address-list.component.html',
  styleUrls: ['./address-list.component.css']
})
export class AddressListComponent implements OnInit {

  addresses: {};
  @Output() selectedItem = new EventEmitter();
  @Output() addSelected = new EventEmitter();
  isAddSelect = false;
  constructor(private json: AddressService) { }

  ngOnInit () {
    this.getJsonData();
  }

  getJsonData() {
    this.json.getAddressData()
        .subscribe(data => {
          this.addresses = data;
          this.selectedItem.emit(this.addresses[0]);
      });
  }
  onSelectRow(item: any) {
    this.selectedItem.emit(item);
  }
  onAddSelect() {
    this.isAddSelect = true;
    this.addSelected.emit(this.isAddSelect);
  }

}
