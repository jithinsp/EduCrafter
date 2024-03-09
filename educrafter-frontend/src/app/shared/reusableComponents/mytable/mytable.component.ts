import { ChangeDetectorRef, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, MatPaginatorIntl } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { PaginatorIntlService } from 'src/app/core/services/paginator/paginator-intl.service';
import { DeleteConfirmationComponent } from '../delete-confirmation/delete-confirmation.component';

@Component({
  selector: 'app-mytable',
  templateUrl: './mytable.component.html',
  styleUrls: ['./mytable.component.css'],
  providers: [{ provide: MatPaginatorIntl, useClass: PaginatorIntlService }]
})
export class MytableComponent implements OnInit, OnChanges {

  @ViewChild(MatPaginator) paginatior !: MatPaginator;
  @ViewChild(MatSort) sort !: MatSort;

  Filterchange(data: Event) {
    const value = (data.target as HTMLInputElement).value;
    this.dataSource.filter = value;
  }

  dataSource: any;
  columnsArray: any[] = [];
  @Input() HeadArray: any[] = [];
  @Input() title: string = '';
  @Input() GridArray: any[] = [];
  @Input() showCreateButton: boolean = true;
  @Input() showEditButton: boolean = true;
  @Input() showDeleteButton: boolean = true;
  @Input() showViewButton: boolean = true;
  @Input() showToggleButton: boolean = true;
  // @Input() showFalseButton: boolean = false;

  @Output() onEdit = new EventEmitter<any>();
  @Output() onToggle = new EventEmitter<any>();
  @Output() onDelete = new EventEmitter<any>();
  @Output() onCreate = new EventEmitter<any>();
  @Output() onView = new EventEmitter<any>();

  constructor(private router: Router,
    private cdr: ChangeDetectorRef,
    public dialog: MatDialog,) {
    this.dataSource = new MatTableDataSource([]);
  }

  ngOnInit(): void {
    this.columnsArray = this.HeadArray.map(col => col.Head);
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['GridArray']) {
      this.updateDataSource();
    }
  }

  updateDataSource(): void {
    // this.dataSource.data = this.GridArray;
    this.dataSource.data = this.GridArray.filter(row => !row.isDeleted);
    this.dataSource.paginator = this.paginatior;
    this.dataSource.sort = this.sort;
  }

  edit(item: any) {
    this.onEdit.emit(item);
  }

  toggle(item: any) {
    const dialogRef = this.dialog.open(DeleteConfirmationComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.onToggle.emit(item);
      }
    });
  }

  delete(item: any) {
    const dialogRef = this.dialog.open(DeleteConfirmationComponent);
    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.onDelete.emit(item);
      }
    });
    }

  openCreate() {
    this.onCreate.emit();
  }

  openView(item: any) {
    this.onView.emit(item);
  }

  isDate(value: any): boolean {
    if (typeof value !== 'string' || !/[a-zA-Z]/.test(value)) {
      return false;
  }
    return value && !isNaN(Date.parse(value));
  }

}
