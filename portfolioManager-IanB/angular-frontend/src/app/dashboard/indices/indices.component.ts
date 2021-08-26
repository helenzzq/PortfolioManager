import { Component, OnInit } from '@angular/core';
import { DatabaseService } from 'src/app/database/database.service'

@Component({
  selector: 'app-indices',
  templateUrl: './indices.component.html',
  styleUrls: ['./indices.component.css']
})
export class IndicesComponent implements OnInit {

  indexStr = "portfolio-manager/indices/famous-indices"
  indexNames = ["", "", "", ""]
  indexValues = ["", "", "", ""]
  symbols = ["", "", "", ""]

  constructor(private databaseService:DatabaseService) { }

  ngOnInit(): void {
    this.getIndices()
  }

  getIndices() {
    this.databaseService.getApiData(this.indexStr).subscribe(
      (data:any) => {
        console.log("Indices Loaded!")
        this.indexNames = Object.keys(data);
        this.indexValues = Object.values(data);
        this.symbols = this.indexValues.map(str => str.charAt(0))
      },
      (err) => console.log(err)
    )

  }

}
