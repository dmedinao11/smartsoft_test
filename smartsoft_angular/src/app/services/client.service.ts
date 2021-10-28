import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Client } from '../models/ClientModels';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  constructor(private http: HttpClient) {}

  getAll(): Observable<Client[] | null> {
    return this.http.get<Client[]>(`${environment.apiRoute}/client/all`);
  }
}
