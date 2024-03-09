import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';

@Component({
  selector: 'app-exam-view',
  templateUrl: './exam-view.component.html',
  styleUrls: ['./exam-view.component.css']
})
export class ExamViewComponent {

  ngOnInit() {
    this.loadAll();
  }

  title = 'Exam';
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
    { 'Head': 'Exam', 'FieldName': 'name' },
    { 'Head': 'Subject', 'FieldName': 'subject' },
    { 'Head': 'Class', 'FieldName': 'classes' },
    { 'Head': 'Slot', 'FieldName': 'timeslot' },
    { 'Head': 'Date', 'FieldName': 'date' },
    { 'Head': 'Action', 'FieldName': '' }
  ];

  loadAll() {
    this.academicsService.getAllExams().subscribe((res) => {
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
    this.router.navigate(['new-exam'], { queryParams: { id, mode } });
  }

  toggle(item: any) {
    this.academicsService.toggleExams(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  deletes(item: any) {
    this.academicsService.deleteExams(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  create() {
    this.router.navigateByUrl("new-exam");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }
}