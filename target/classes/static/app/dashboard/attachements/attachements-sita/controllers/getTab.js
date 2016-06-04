// get un tableau de jour d'un mois donné
	@Override
	public List<Integer> getTabDaysMonth(String dateString) {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int month = 0;
		try {
			cal.setTime(df.parse(dateString));
			month = cal.get(Calendar.MONTH) +1;
			System.out.println("month is : "+month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(days);
		List<Integer> d = new ArrayList<Integer>();
		for (int i = 1; i <= days; i++) {
			d.add(i);
			System.out.println(i);
		}
		return d;
	}
}

public int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH)+1;
		return month;
	}