import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';

@Component({
  selector: 'app-slot-view',
  templateUrl: './slot-view.component.html',
  styleUrls: ['./slot-view.component.css']
})
export class SlotViewComponent {

  ngOnInit() {
    this.loadAll();
  }

  title = 'Slots';
  showCreateButton = true;
  showEditButton = true;
  showToggleButton = true;
  showDeleteButton = true;
  showViewButton = false;

  constructor(private academicsService: AcademicsService,
    private router: Router) { }

  list: any[] = [];
  headArray = [
    { 'Head': 'No', 'FieldName': 'no' },
    { 'Head': 'Slot', 'FieldName': 'name' },
    { 'Head': 'Start time', 'FieldName': 'startTime' },
    { 'Head': 'End Time', 'FieldName': 'endTime' },
    { 'Head': 'Action', 'FieldName': '' }
  ];

  loadAll() {
    this.academicsService.getAllSlots().subscribe((res) => {
      this.list = res;
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  edit(item: any) {
    const mode = 'edit';
    console.log(item.id);
    const id = item.id;
    this.router.navigate(['new-slot'], { queryParams: { id, mode } });
  }

  toggle(item: any) {
    this.academicsService.toggleSlots(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  deletes(item: any) {
    this.academicsService.deleteSlots(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  create() {
    this.router.navigateByUrl("new-slot");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }
}