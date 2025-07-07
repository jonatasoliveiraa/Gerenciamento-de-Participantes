import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SignatureCard } from '../models/signatureCard';


@Injectable({
  providedIn: 'root',
})
export class SignatureCardService {
  private base = '/api/cards';

  constructor(private http: HttpClient) {}

  create(participantId: number, card: SignatureCard): Observable<SignatureCard> {
    return this.http.post<SignatureCard>(
      `${this.base}/${participantId}`,
      card
    );
  }

  update(id: number, card: SignatureCard): Observable<SignatureCard> {
    return this.http.put<SignatureCard>(`${this.base}/${id}`, card);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.base}/${id}`);
  }
}
