import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ResultService } from 'src/app/services/result.service';

@Component({
  selector: 'app-load-result',
  templateUrl: './load-result.component.html',
  styleUrls: ['./load-result.component.css']
})
export class LoadResultComponent implements OnInit{
  result: any = {};


  constructor(
    private activatedRoute: ActivatedRoute,
    private resultService: ResultService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCurrentResult();
    this.preventBack();
  }

  loadCurrentResult(): void {
    this.resultService.getCurrentResult().subscribe(
      (data: any) => {
        this.result = data;
        console.log("Result Loaded:", this.result);
      },
      error => {
        console.error("Error loading result", error);
      }
    );
  }

  preventBack(): void {
    history.pushState(null, 'null', location.href);
    window.onpopstate = () => {
      history.pushState(null, 'null', location.href);
    };
  }

  printPage(): void {
    window.print();
  }
}
