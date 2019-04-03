import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AddressService {
  constructor(private http: HttpClient) { }

  getAddressData() {
    return this.http.get('assets/address.json');
  }
}
