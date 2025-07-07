import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Participant } from '../models/participant';
import { Page } from '../models/page';

@Injectable({
  providedIn: 'root',
})
export class ParticipantService {
  private apiUrl = '/api/participants';
  constructor(private http: HttpClient) {}

  getAll(page = 0, size = 20): Observable<Page<Participant>> {
    const params = new HttpParams()
      .set('page', String(page))
      .set('size', String(size));

    return this.http.get<Page<Participant>>(this.apiUrl, { params });
  }

  getById(id: number): Observable<Participant> {
    return this.http.get<Participant>(`${this.apiUrl}/${id}`);
  }

  create(data: Participant): Observable<Participant> {
    return this.http.post<Participant>(this.apiUrl, data);
  }

  update(id: number, data: Participant): Observable<Participant> {
    return this.http.put<Participant>(`${this.apiUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
