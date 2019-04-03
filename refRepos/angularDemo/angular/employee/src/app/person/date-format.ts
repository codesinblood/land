import { pipe } from 'rxjs';
import { PipeTransform, Pipe } from '@angular/core';
import { formatDate, DatePipe } from '@angular/common';

@Pipe ({
  name: 'dateFormat'
})
export class DateFormat extends DatePipe implements PipeTransform {
  transform(value: any) {
    return super.transform(value, 'dd-MM-yyyy');
  }
 }
