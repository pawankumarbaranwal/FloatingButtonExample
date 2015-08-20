package com.floatingbuttonexample;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    ListView listView;
    Customer customer;
    List<Customer> customerList = new ArrayList<Customer>();
    Context context = this;
    AdapterClass adapter;
    public static final String TAG_SORT_NAME = "sort by name";
    public static final String TAG_SORT_PHONE = "sort by phone";
    public static final String TAG_ADD_CUSTOMER = "add customer";
    public static final String TAG_MAIN = "floatingbutton";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        addDataInList();
        adapter = new AdapterClass();
        listView.setAdapter(adapter);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.button_color);

        FloatingActionButton floatingActionButton = new FloatingActionButton.Builder(this).setContentView(imageView).build();

        //floatingActionButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        floatingActionButton.setTag(TAG_MAIN);

        ImageView sortByNameImageView = new ImageView(this);
        sortByNameImageView.setImageResource(R.mipmap.sort);
        ImageView sortByPhoneImageView = new ImageView(this);
        sortByPhoneImageView.setImageResource(R.mipmap.sort);
        ImageView addNewCustomerImageView = new ImageView(this);
        addNewCustomerImageView.setImageResource(R.mipmap.addicon);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton sortByName = itemBuilder.setContentView(sortByNameImageView).build();
        SubActionButton sortByPhone = itemBuilder.setContentView(sortByPhoneImageView).build();
        SubActionButton addNewCustomer = itemBuilder.setContentView(addNewCustomerImageView).build();

        FloatingActionMenu menu = new FloatingActionMenu.Builder(this)
                .addSubActionView(sortByName)
                .addSubActionView(sortByPhone)
                .addSubActionView(addNewCustomer)
                .attachTo(floatingActionButton)
                .build();

        sortByName.setTag(TAG_SORT_NAME);
        sortByPhone.setTag(TAG_SORT_PHONE);
        addNewCustomer.setTag(TAG_ADD_CUSTOMER);

        //floatingActionButton.setOnClickListener(this);
        sortByName.setOnClickListener(this);
        sortByPhone.setOnClickListener(this);
        addNewCustomer.setOnClickListener(this);
    }


    private void addDataInList() {
        customer = new Customer();
        customer.setName("Pawan");
        customer.setPhoneNumber("9902765235");
        customerList.add(customer);
        customer = new Customer();
        customer.setName("Nadeem");
        customer.setPhoneNumber("8792865626");
        customerList.add(customer);
        customer = new Customer();
        customer.setName("Sahil");
        customer.setPhoneNumber("9643338965");
        customerList.add(customer);
        customer = new Customer();
        customer.setName("Santosh");
        customer.setPhoneNumber("9645689650");
        customerList.add(customer);
        customer = new Customer();
        customer.setName("Javed");
        customer.setPhoneNumber("8649887329");
        customerList.add(customer);
        customer = new Customer();
        customer.setName("Naman");
        customer.setPhoneNumber("9644448965");
        customerList.add(customer);
        customer = new Customer();
        customer.setName("Sidhant");
        customer.setPhoneNumber("8880568965");
        customerList.add(customer);
        customer = new Customer();
        customer.setName("Naveen");
        customer.setPhoneNumber("8009892389");
        customerList.add(customer);
        customer = new Customer();
        customer.setName("Baba");
        customer.setPhoneNumber("7638390878");
        customerList.add(customer);
    }


    @Override
    public void onClick(View v) {

        /*if (v.getTag().equals(TAG_MAIN)) {
            Toast.makeText(this, TAG_MAIN, Toast.LENGTH_SHORT).show();
        }*/
        if (v.getTag().equals(TAG_SORT_NAME)) {
            Collections.sort(customerList, new Comparator<Customer>() {
                @Override
                public int compare(Customer lhs, Customer rhs) {
                    return lhs.getName().toUpperCase().compareTo(rhs.getName().toUpperCase());
                }
            });
            Toast.makeText(this, TAG_SORT_NAME, Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        } else if (v.getTag().equals(TAG_SORT_PHONE)) {
            Collections.sort(customerList, new Comparator<Customer>() {
                @Override
                public int compare(Customer lhs, Customer rhs) {
                    return lhs.getPhoneNumber().compareTo(rhs.getPhoneNumber());
                }
            });
            Toast.makeText(this, TAG_SORT_PHONE, Toast.LENGTH_SHORT).show();
            adapter.notifyDataSetChanged();
        } else if (v.getTag().equals(TAG_ADD_CUSTOMER)) {
            customer = new Customer();
            final Dialog dialog = new Dialog(MainActivity.this);
            dialog.setContentView(R.layout.dialogbox_add_anoather_customer);
            final EditText customerName = (EditText) dialog.findViewById(R.id.edCustomerNameDialogBox);
            final EditText phoneNo = (EditText) dialog.findViewById(R.id.edPhoneNoDialogBox);
            final Button cancel = (Button) dialog.findViewById(R.id.btnCancelDialogForAddCustomer);
            final Button ok = (Button) dialog.findViewById(R.id.btnOKDialogForAddCustomer);
            dialog.setTitle("Add Customer");
            dialog.show();
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customer.setName(customerName.getText() + "");
                    customer.setPhoneNumber(phoneNo.getText() + "");
                    customerList.add(customer);
                    adapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            });
            Toast.makeText(this, TAG_ADD_CUSTOMER, Toast.LENGTH_SHORT).show();
        }
    }

    public class AdapterClass extends BaseAdapter {

        @Override
        public int getCount() {
            return customerList.size();
        }

        @Override
        public Object getItem(int position) {
            return customerList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView nameTextView;
            TextView phoneNumberTextView;

            ViewHolder(View view) {
                nameTextView = (TextView) view.findViewById(R.id.name);
                phoneNumberTextView = (TextView) view.findViewById(R.id.phoneNumber);
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.rowforlistview, parent, false);
                viewHolder = new ViewHolder(row);
                row.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) row.getTag();
            }
            Customer c = customerList.get(position);
            viewHolder.nameTextView.setText(c.getName());
            viewHolder.phoneNumberTextView.setText(c.getPhoneNumber());
            viewHolder.nameTextView.setTag(c);
            return row;
        }
    }
}
