import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExaminationQuestionsComponent } from './examination-questions.component';

describe('ExaminationQuestionsComponent', () => {
  let component: ExaminationQuestionsComponent;
  let fixture: ComponentFixture<ExaminationQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExaminationQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExaminationQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
