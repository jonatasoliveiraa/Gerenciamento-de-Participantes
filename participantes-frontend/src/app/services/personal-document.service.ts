import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PersonalDocument } from '../models/personalDocument';

@Injectable({
  providedIn: 'root',
})
export class PersonalDocumentService {
  private base = '/api/documents';
  constructor(private http: HttpClient) {}

  create(participantId: number, document: PersonalDocument): Observable<PersonalDocument> {
    return this.http.post<PersonalDocument>(
      `${this.base}/${participantId}`,
      document
    );
  }

  update(id: number, document: PersonalDocument): Observable<PersonalDocument> {
    return this.http.put<PersonalDocument>(`${this.base}/${id}`, document);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/${id}`);
  }
}
