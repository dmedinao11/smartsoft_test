import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Invoice } from '../models/InvoiceModels';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class InvoiceService {
  constructor(private http: HttpClient) {}

  save(toSave: Invoice): Observable<Invoice> {
    return this.http.post<Invoice>(`${environment.apiRoute}/invoice`, toSave);
  }
}
