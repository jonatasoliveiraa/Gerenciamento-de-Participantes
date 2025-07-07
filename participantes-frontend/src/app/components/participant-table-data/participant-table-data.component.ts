import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ParticipantService } from '../../services/participant.service';
import { Participant } from '../../models/participant';
import { Page } from '../../models/page';
import { MatPaginatorModule, PageEvent } from '@angular/material/paginator';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { SelectionModel } from '@angular/cdk/collections';
import { Router, RouterModule } from '@angular/router';
@Component({
  selector: 'app-participant-table-data',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatCheckboxModule,
    MatPaginatorModule,
    RouterModule,
  ],
  templateUrl: './participant-table-data.component.html',
  styleUrl: './participant-table-data.component.scss',
})
export class ParticipantTableDataComponent implements OnInit {
  @Output()
  selectionChange = new EventEmitter<Participant[]>();
  dataSource = new MatTableDataSource<Participant>([]);
  selection = new SelectionModel<Participant>(true, []);
  totalElements = 0;
  pageSize = 20;
  pageIndex = 0;

  displayedColumns: string[] = [
    'select',
    'id',
    'externalCode',
    'fullName',
    'cpf',
    'cellPhone',
    'digitalSignature',
    'status',
  ];

  constructor(private service: ParticipantService, private router: Router) {}

  ngOnInit() {
    this.loadPage();
  }

  loadPage() {
    this.service
      .getAll(this.pageIndex, this.pageSize)
      .subscribe((page: Page<Participant>) => {
        this.dataSource.data = page.content;
        this.totalElements = page.totalElements;
        this.pageIndex = page.number;
        this.pageSize = page.size;
        this.selection.clear();
        this.emitSelection();
      });
  }

  private emitSelection() {
    this.selectionChange.emit(this.selection.selected);
  }

  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadPage();
  }

  toggleAllRows() {
    if (this.selection.selected.length === this.dataSource.data.length) {
      this.selection.clear();
    } else {
      this.selection.select(...this.dataSource.data);
    }
    this.emitSelection();
  }

  rowToggle(row: Participant) {
    this.selection.toggle(row);
    this.emitSelection();
  }

  goToProfile(row: Participant) {
    this.router.navigate(['/profile', row.id]);
  }
}
