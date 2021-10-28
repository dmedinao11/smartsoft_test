import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ControlContainer, FormArray, FormGroup } from '@angular/forms';
import { Observable, of } from 'rxjs';
import { Product } from 'src/app/models/ProductModel';
import { map, startWith, tap } from 'rxjs/operators';

@Component({
  selector: 'app-invoice-product',
  templateUrl: './invoice-product.component.html',
  styleUrls: ['./invoice-product.component.css'],
})
export class InvoiceProductComponent implements OnInit {
  @Input() formIndex = 0;
  @Input() products: Product[] = [];
  @Output('delete') outputEventEmitter: EventEmitter<number> =
    new EventEmitter();
  filteredProducts: Observable<Product[]> = of([]);
  productForm?: FormGroup;
  selectedProduct?: Product;
  totalPrice: number = 0;

  constructor(private controlContainer: ControlContainer) {
    this.displayProducts = this.displayProducts.bind(this);
  }

  ngOnInit(): void {
    const parentForm = this.controlContainer.control as FormGroup;
    const productsList = parentForm.get('productDTOList') as FormArray;
    this.productForm = productsList.at(this.formIndex) as FormGroup;
    this.setFormChangeObservers();
  }

  displayProducts(value: number) {
    const filteredProducts = this.products.filter(
      (product) => product.id === value
    );

    return filteredProducts.length > 0 ? filteredProducts[0].name : '';
  }

  setFormChangeObservers() {
    this.filteredProducts = this.productForm
      ?.get('productId')
      ?.valueChanges.pipe(
        startWith(''),
        tap((newValue: number | string) => {
          this.selectedProduct =
            typeof newValue == 'number'
              ? this.products.find((product) => product.id === newValue)
              : undefined;
        }),
        map((newValue: number | string) => {
          if (typeof newValue === 'number') return [];

          return this.products.filter((product) =>
            product.name.toLowerCase().includes(newValue.toLowerCase())
          );
        })
      ) as Observable<Product[]>;

    this.productForm?.get('quantity')?.valueChanges.subscribe((newQuantity) => {
      if (this.productForm?.get('quantity')?.valid && this.selectedProduct) {
        this.totalPrice = newQuantity * this.selectedProduct.price;
      }
    });
  }

  deleteThis() {
    this.outputEventEmitter.emit(this.formIndex);
  }
}
