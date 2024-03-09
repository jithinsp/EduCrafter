import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeepaymentComponent } from './feepayment.component';

describe('FeepaymentComponent', () => {
  let component: FeepaymentComponent;
  let fixture: ComponentFixture<FeepaymentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FeepaymentComponent]
    });
    fixture = TestBed.createComponent(FeepaymentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
