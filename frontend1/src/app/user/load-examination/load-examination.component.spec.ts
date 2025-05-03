import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadExaminationComponent } from './load-examination.component';

describe('LoadExaminationComponent', () => {
  let component: LoadExaminationComponent;
  let fixture: ComponentFixture<LoadExaminationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoadExaminationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoadExaminationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
