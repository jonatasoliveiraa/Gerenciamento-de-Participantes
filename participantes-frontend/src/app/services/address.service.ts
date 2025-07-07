import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Address } from '../models/address';

@Injectable({ providedIn: 'root' })
export class AddressService {
  private base = '/api/address';

  constructor(private http: HttpClient) {}

  create(participantId: number, address: Address): Observable<Address> {
    return this.http.post<Address>(`${this.base}/${participantId}`, address);
  }

  update(id: number, address: Address): Observable<Address> {
    return this.http.put<Address>(`${this.base}/${id}`, address);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/${id}`);
  }
}
