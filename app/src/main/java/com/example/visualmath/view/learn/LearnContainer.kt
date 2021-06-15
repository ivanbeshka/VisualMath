package com.example.visualmath.view.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.visualmath.R
import com.example.visualmath.view.*

class LearnContainer : Fragment() {

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar()
    }

    private fun setActionBar() {
        setHasOptionsMenu(true)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.show()
        actionBar?.title = arguments?.getString(typeKey, "")!!
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.container_learn, container, false)

        viewPager = view.findViewById(R.id.viewPager)
        val type = arguments?.getString(typeKey, "")!!
        setViewPagerAdapter(type)

        return view
    }

    private fun setViewPagerAdapter(type: String) {
        when (type) {
            sample -> {
                viewPager.adapter =
                    ViewPagerAdapter(getSampleFragments(), parentFragmentManager, lifecycle)
            }
            _1zn -> {
                viewPager.adapter =
                    ViewPagerAdapter(get1ZnNumbers(), parentFragmentManager, lifecycle)
            }
            _2zn -> {
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            activity?.onBackPressed()
            return true
        }
        return false
    }

    private fun getSampleFragments(): List<Fragment> {
        return listOf(
            AbacusLearnFragment(),
            SampleFragment(
                "5", "1", "6", "Так как верхние косточки\n" +
                        " отвечают за пятерки, \n" +
                        "а нижние за единицы, \n" +
                        "то в данном случае\n" +
                        " у нас получится\n" +
                        " число 6",
                resources.getDrawable(R.drawable.sample2)
            ),
            SampleFragment(
                "5", "2", "7", "Добавляя косточки в\n" +
                        "нижнем ряду, мы\n" +
                        "увеличиваем число\n" +
                        "на единицу",
                resources.getDrawable(R.drawable.sample3)
            ),
            SampleFragment(
                "0", "10", "10", "Если же мы захотим \n" +
                        "показать число 10,\n" +
                        "то нам придется \n" +
                        "сдвинуть косточку \n" +
                        "в разряде десяток",
                resources.getDrawable(R.drawable.sample4)
            )
        )
    }

    private fun get1ZnNumbers(): List<Fragment> {
        return listOf(
            SampleFragment(
                "", "", "", "Попробуем сложить \n" +
                        "числа 5 и 6",
                resources.getDrawable(R.drawable.sample1_big)
            ),
            SampleFragment(
                "5", "", "", "Для этого сначала \n" +
                        "откладываем число пять",
                resources.getDrawable(R.drawable.plus_1zn_1)
            ),
            SampleFragment(
                "5", "4", "9", "Затем начинаем \n" +
                        "прибавлять по единице\n" +
                        "до тех пор, пока не\n" +
                        "закончатся косточки.\n",
                resources.getDrawable(R.drawable.plus_1zn_2)
            ),
            SampleFragment(
                "0", "10", "10", "Когда косточки в \n" +
                        "разряде единиц \n" +
                        "закончились, мы их \n" +
                        "убираем и \n" +
                        "добавляем косточку\n" +
                        " в разряде\n" +
                        "десятков",
                resources.getDrawable(R.drawable.plus_1zn_3)
            ),
            SampleFragment(
                "0", "11", "11", "У нас осталась еще одна\n" +
                        "не прибавленная единица.\n" +
                        "Добавляем косточку\n" +
                        "в разряде единиц.\n" +
                        "Итого у нас получилось\n" +
                        "11",
                resources.getDrawable(R.drawable.plus_1zn_4)
            ),
            LearnQuizFragment(
                "Попытайтесь решить \n" +
                        "несколько примеров \n" +
                        "сами \n" + "Вы можете\n" +
                        "передвигать нужные \n" +
                        "вам косточки", btnTopText = "Я ничего не понимаю,\n" +
                        "верните меня обратно", btnBottomText = "Давайте же начнем",
                abacus = resources.getDrawable(R.drawable.sample1_big)
            ),
            LearnQuizFragment(
                "Сложите числа \n" +
                        "3 и 1 \n" +
                        "и введите свой\n" +
                        "ответ в строку \n" +
                        "ниже", answer = "4", isVisibleCat = true,
                abacus = resources.getDrawable(R.drawable.sample1_big)
            ),
            LearnQuizFragment(
                "Сложите числа \n" +
                        "5 и 3\n" +
                        "и введите свой\n" +
                        "ответ в строку \n" +
                        "ниже", answer = "8", isVisibleCat = true,
                abacus = resources.getDrawable(R.drawable.sample1_big)
            ),
            LearnQuizFragment(
                "Сложите числа \n" +
                        "8 и 9 \n" +
                        "и введите свой\n" +
                        "ответ в строку \n" +
                        "ниже", answer = "17", isVisibleCat = true,
                abacus = resources.getDrawable(R.drawable.sample1_big)
            ),
        )
    }
}