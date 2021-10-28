import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Client } from 'src/app/models/ClientModels';
import { Product } from 'src/app/models/ProductModel';
import { ClientService } from 'src/app/services/client.service';
import { ProductService } from 'src/app/services/product.service';
import { map, startWith } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { InvoiceProduct } from 'src/app/models/InvoiceModels';
import { InvoiceService } from 'src/app/services/invoice.service';

@Component({
  selector: 'app-invoice-form',
  templateUrl: './invoice-form.component.html',
  styleUrls: ['./invoice-form.component.css'],
})
export class InvoiceFormComponent implements OnInit {
  clientsList: Client[] = [];
  productsList: Product[] = [];
  form: FormGroup;
  filteredClients: Observable<Client[]> = of([]);
  isLoading: boolean = true;

  constructor(
    private clientService: ClientService,
    private productService: ProductService,
    private invoiceService: InvoiceService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({});
    this.displayClients = this.displayClients.bind(this);
  }

  ngOnInit(): void {
    this.createForm();

    this.loadData();
  }

  private createForm() {
    this.form = this.fb.group({
      clientId: [0, [Validators.required, Validators.min(1)]],
      productDTOList: this.fb.array([
        this.fb.group({
          productId: [0, [Validators.required, Validators.min(1)]],
          quantity: [0, [Validators.required, Validators.min(1)]],
        }),
      ]),
    });

    this.registerValueObservers();
  }

  private loadData() {
    this.clientService.getAll().subscribe((resp) => {
      if (resp) this.clientsList = resp;
      this.isLoading =
        this.clientsList.length < 0 && this.productsList.length < 0;
    });

    this.productService.getAll().subscribe((resp) => {
      if (resp) this.productsList = resp;
      this.isLoading =
        this.clientsList.length < 0 && this.productsList.length < 0;
    });
  }

  displayClients(value: number): string {
    const filteredClients = this.clientsList.filter(
      (client) => client.id === value
    );

    return filteredClients.length > 0
      ? filteredClients[0].name.concat(' ', filteredClients[0].lastName)
      : '';
  }

  addProductForm() {
    const toAdd = this.fb.group(new InvoiceProduct());
    this.productsForm.push(toAdd);
  }

  deleteProductForm(index: number) {
    this.productsForm.removeAt(index);
  }

  get productsForm(): FormArray {
    return this.form.get('productDTOList') as FormArray;
  }

  submit() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
    } else {
      this.invoiceService.save(this.form.value).subscribe(() => {
        this.isLoading = false;
        this.loadData();
        this.createForm();
      });
    }
  }

  private registerValueObservers() {
    this.filteredClients = this.form.get('clientId')?.valueChanges.pipe(
      startWith(''),
      map((value: number | string) => {
        if (typeof value === 'number') return [];

        return this.clientsList.filter(
          (client) =>
            client.name.toLowerCase().includes(value.toLowerCase()) ||
            client.lastName.toLowerCase().includes(value.toLowerCase())
        );
      })
    ) as Observable<Client[]>;
  }
}
