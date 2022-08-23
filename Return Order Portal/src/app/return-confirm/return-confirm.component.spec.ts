import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnConfirmComponent } from './return-confirm.component';

describe('ReturnConfirmComponent', () => {
  let component: ReturnConfirmComponent;
  let fixture: ComponentFixture<ReturnConfirmComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReturnConfirmComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReturnConfirmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
