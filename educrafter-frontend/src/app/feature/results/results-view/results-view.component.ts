import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';

@Component({
  selector: 'app-results-view',
  templateUrl: './results-view.component.html',
  styleUrls: ['./results-view.component.css']
})
export class ResultsViewComponent {

  ngOnInit() {
    this.loadAll();
  }

  title = 'Results';
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
    { 'Head': 'Subject', 'FieldName': 'subject' },
    { 'Head': 'Class', 'FieldName': 'classes' },
    { 'Head': 'Exam', 'FieldName': 'exam' },
    { 'Head': 'Student', 'FieldName': 'studentId' },
    { 'Head': 'Mark', 'FieldName': 'mark' },
    { 'Head': 'Action', 'FieldName': '' }
  ];

  loadAll() {
    this.academicsService.getAllResults().subscribe((res) => {
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
    this.router.navigate(['new-result'], { queryParams: { id, mode } });
  }

  toggle(item: any) {
    this.academicsService.toggleResults(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  deletes(item: any) {
    this.academicsService.deleteResults(item).subscribe((res) => {
      console.log(res);
      this.loadAll();
    },
      error => {
        console.error('Error fetching data:', error);
      }
    );
  }

  create() {
    this.router.navigateByUrl("new-result");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }
}
