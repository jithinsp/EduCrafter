import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AcademicsService } from 'src/app/core/services/academics/academics.service';
import { AdminService } from 'src/app/core/services/admin/admin.service';

@Component({
  selector: 'app-classes',
  templateUrl: './classes.component.html',
  styleUrls: ['./classes.component.css']
})
export class ClassesComponent {


  ngOnInit() {
    this.loadClasses();
  }

  showCreateButton = true;
  showEditButton = true;
  showDeleteButton = true;
  showViewButton = false;
  showToggleButton = true;
  title = 'Class';

  constructor(private academicsService: AcademicsService,
    private router: Router) { }

  classList: any[] = [];
  headArray = [
    { 'Head': 'No', 'FieldName': 'no' },
    { 'Head': 'Class Name', 'FieldName': 'classes' },
    { 'Head': 'Division', 'FieldName': 'division' },
    { 'Head': 'Class Teacher', 'FieldName': 'classTeacher' },
    { 'Head': 'Action', 'FieldName': '' }
  ];

  loadClasses() {
    this.academicsService.getAllClasses().subscribe((res) => {
      this.classList = res;
    },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  editClass(item: any) {
      const mode ='edit';
      console.log(item.id);
      const id = item.id;
      this.router.navigate(['new-classes'], { queryParams: { id, mode } });
  }

  toggle(item: any) {
    console.log(item);
    // debugger;
    this.academicsService.toggleClass(item).subscribe((res) => {
      console.log(res);
      this.loadClasses();
    },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  deleteClass(item: any) {
    console.log(item);
    // debugger;
    this.academicsService.deleteClasses(item).subscribe((res) => {
      console.log(res);
      this.loadClasses();
    },
      error => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  createClass() {
    this.router.navigateByUrl("new-classes");
    // this.router.navigate(['register'], { queryParams: { role: this.param } });
  }
}
