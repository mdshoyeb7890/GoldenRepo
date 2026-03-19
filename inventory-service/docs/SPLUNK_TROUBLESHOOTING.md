# Splunk logs not visible – troubleshooting

## 1. Splunk and HEC must be running

- **Splunk** must be installed and running (e.g. `splunk start`).
- **Splunk Web** is usually on **http://localhost:8000** (you must be able to open it).
- **HTTP Event Collector (HEC)** must be enabled and listening on **port 8088**.

**Enable HEC in Splunk:**

1. Open Splunk Web → **Settings** → **Data inputs** → **HTTP Event Collector**.
2. Click **Global Settings**.
3. Set **All Tokens** to **Enabled**.
4. Save.

---

## 2. Create and use an HEC token

1. **Settings** → **Data inputs** → **HTTP Event Collector** → **New Token**.
2. Set a **Name** (e.g. `inventory-service`).
3. **Index**: create/select **inventory_api_dev** (must exist).
4. **Source type**: e.g. `spring-boot` (optional).
5. Save and **copy the token**.
6. In `log4j2.xml` the **token** must match this value (or set env `SPLUNK_HEC_TOKEN` to it).  
   Default in config: `8127492c-a7c7-4d62-98dd-238ba0e4b99f` — if you created a new token, replace it or use the env variable.

---

## 3. Index must exist

1. **Settings** → **Indexes** → **New Index**.
2. **Index name**: `inventory_api_dev` (same as in `log4j2.xml`).
3. Save.

---

## 4. App is running and logging

- Start the app: `mvn spring-boot:run` or run `InventoryServiceApplication`.
- Generate some traffic (e.g. call `POST/GET /api/v1/inventory`) so INFO logs are produced.
- You should see logs in the **console**; the same events are sent to Splunk.

---

## 5. Search in Splunk

In **Search & Reporting** run:

```text
index=inventory_api_dev
```

Or narrow by source:

```text
index=inventory_api_dev source=inventory-service
```

- Set the **time range** (e.g. **Last 15 minutes** or **All time**).

---

## 6. If still no logs: check for errors

- In `log4j2.xml`, temporarily set the top line to:
  - `<Configuration status="DEBUG">`
- Restart the app and watch the **console** for Log4j2/Splunk appender errors (e.g. connection refused, 401, 404).
- Then set it back to `status="WARN"`.

---

## Quick checklist

| Step | Check |
|------|--------|
| 1 | Splunk running, http://localhost:8000 opens |
| 2 | HEC enabled (port 8088), token created and copied |
| 3 | Token in `log4j2.xml` (or `SPLUNK_HEC_TOKEN`) matches Splunk |
| 4 | Index `inventory_api_dev` exists in Splunk |
| 5 | App started and API called so logs are generated |
| 6 | Search: `index=inventory_api_dev`, time range = Last 15 min / All time |
