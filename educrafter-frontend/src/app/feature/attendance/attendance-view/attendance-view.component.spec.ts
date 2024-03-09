import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttendanceViewComponent } from './attendance-view.component';

describe('AttendanceViewComponent', () => {
  let component: AttendanceViewComponent;
  let fixture: ComponentFixture<AttendanceViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AttendanceViewComponent]
    });
    fixture = TestBed.createComponent(AttendanceViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
