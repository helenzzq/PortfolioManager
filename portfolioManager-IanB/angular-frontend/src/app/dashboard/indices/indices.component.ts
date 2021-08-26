import { Component, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service'

@Component({
  selector: 'app-indices',
  templateUrl: './indices.component.html',
  styleUrls: ['./indices.component.css']
})
export class IndicesComponent implements OnInit {

  indexStr = "portfolio-manager/indices"
  indicesData = [{}, {}, {}, {}]

  constructor(private databaseService:DatabaseService) { }

  ngOnInit(): void {
    this.getIndices();
  }

  getIndices() {
    this.databaseService.getApiData(this.indexStr).subscribe(
      (data:any) => {
        this.indicesData = [data[0], data[1], data[2], data[3]]
      },
      (err) => console.log(err)
    )

  }

}
