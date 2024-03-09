import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewNoticeComponent } from './new-notice.component';

describe('NewNoticeComponent', () => {
  let component: NewNoticeComponent;
  let fixture: ComponentFixture<NewNoticeComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewNoticeComponent]
    });
    fixture = TestBed.createComponent(NewNoticeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
