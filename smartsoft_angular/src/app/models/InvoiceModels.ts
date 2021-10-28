export class Invoice {
  clientId: number = 0;
  productDTOList: InvoiceProduct[] = [];
}

export class InvoiceProduct {
  quantity: number = 0;
  productId: number = 0;
  price?: number = 0;
  productName?: string = '';
}
