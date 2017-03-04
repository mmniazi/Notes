# Cron Jobs
- Cron jobs are created using `crontab -e`
- Each line represents each command
- Format is `minute hour dayofmonth month dayofweek command`
- Use * if you want to run command on every instance of that time.
Forexample * in month will run it every month
- `01,31 04,05 1-15 1,6 * command` will run command at 01 and 31 past the hours of 4:00am and 5:00am on the 1st through the 15th of every January and June.
- You may want to run a script some number of times per time unit. For example if you want to run it every 10 minutes use the following crontab entry (runs on minutes divisible by 10: 0, 10, 20, 30, etc.)
`*/10 * * * * command`

<div style="page-break-after: always;"></div>

## Cron Time Constants
In place of 5 time quantifiers you can use a predefined time constants.
`@reboot command`
|String|Meaning
|-|-
|@reboot|Run once, at startup
|@yearly|Run once a year, "0 0 1 1 *"
|@annually|(same as @yearly)
|@monthly|Run once a month, "0 0 1 * *".
|@weekly|Run once a week, "0 0 * * 0".
|@daily|Run once a day, "0 0 * * *".
|@midnight|(same as @daily)
|@hourly|Run once an hour, "0 * * * *".

